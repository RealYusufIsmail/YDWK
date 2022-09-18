/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDWK contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwk.impl.event.recieve.adapter

import io.github.realyusufismail.ydwk.impl.event.Event
import io.github.realyusufismail.ydwk.impl.event.recieve.IEvent
import io.github.realyusufismail.ydwk.impl.event.recieve.util.ClassWalker
import io.github.realyusufismail.ydwk.impl.event.update.IEventUpdate
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * Inspired from JDA's
 * [ListenersAdapter](https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/api/hooks/ListenerAdapter.java)
 */
abstract class EventAdapter : IEvent {
    fun onBasicEvent(event: Event?) {}
    fun onBasicUpdate(eventUpdate: IEventUpdate<*, *>?) {}

    /**
     * This method is called when an event is received.
     *
     * @param event The event that was received.
     */
    override fun onEvent(event: Event) {
        onBasicEvent(event)
        if (event is IEventUpdate<*, *>) onBasicUpdate(event as IEventUpdate<*, *>)
        for (clazz in ClassWalker.range(event.javaClass, Event::class.java)) {
            if (unresolved!!.contains(clazz)) continue
            val mh = methods.computeIfAbsent(clazz) { clazz: Class<*> -> findMethod(clazz) }
            if (mh == null) {
                if (clazz != null) {
                    unresolved!!.add(clazz)
                }
                continue
            }
            try {
                mh.invoke(this, event)
            } catch (throwable: Throwable) {
                if (throwable is RuntimeException) throw throwable
                if (throwable is Error) throw throwable
                throw IllegalStateException(throwable)
            }
        }
    }

    companion object {
        private val lookup = MethodHandles.lookup()
        private val methods: ConcurrentMap<Class<*>, MethodHandle?> = ConcurrentHashMap()
        private var unresolved: MutableSet<Class<*>>? = null

        init {
            unresolved = ConcurrentHashMap.newKeySet()
            unresolved?.let {
                Collections.addAll(
                    it,
                    Any::class.java, // Objects aren't events
                    Event::class.java, // onEvent is final and would never be found
                    IEventUpdate::class.java, // onBasicUpdate has already been called
                    Event::class.java // onBasicEvent has already been called
                    )
            }
        }

        private fun findMethod(clazz: Class<*>): MethodHandle? {
            var name = clazz.simpleName
            val type = MethodType.methodType(Void.TYPE, clazz)
            try {
                name = "on" + name.substring(0, name.length - "Event".length)
                return lookup.findVirtual(EventAdapter::class.java, name, type)
            } catch (
                ignored: NoSuchMethodException) {} // this means this is probably a custom event!
            catch (ignored: IllegalAccessException) {}
            return null
        }
    }
}
