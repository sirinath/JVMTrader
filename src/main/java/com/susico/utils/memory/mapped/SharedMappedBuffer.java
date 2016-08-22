/*
 * Microtrader is available under either the terms of the Apache License, Version 2.0 (ASF 2.0)
 * the Academic Free License Version 3.0, (AFL 3.0) or MIT License (MIT). As a recipient of
 * Microtrader, you may choose which license to receive this code or content under
 * (except as noted in per-module LICENSE files). Some modules may not be the copyright
 * of the Suminda Sirinath Salpitikorala Dharmasena and Project Contributors.
 * These modules contain explicit declarations of copyright in both the LICENSE files
 * in the directories in which they reside and in the code or content itself.
 *
 * No external contributions are allowed under licenses which are fundamentally
 * incompatible with the ASL 2.0, AFL 3.0 and MIT that Microtrader is distributed under.
 * By contributing to this project by means of including but not limited to patches,
 * pull requests, code submissions, issues, bug report, code snippets, discussions,
 * email message, chat messages such content will be licensed under the terms of
 * ASL 2.0, AFL 3.0 and MIT where the recipients are free to choose under which license
 * code or content is received under.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ______________________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.susico.utils.memory.mapped;

import com.susico.utils.io.IOUtils;
import com.susico.utils.memory.MemoryRange;
import com.susico.utils.memory.SharedBuffer;
import com.susico.utils.memory.heap.rc.RCObject;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sirin_000 on 17/09/2015.
 */
public final class SharedMappedBuffer implements SharedBuffer<MappedByteBuffer> {
    protected final SharedMappedResource sharedMappedResource;
    protected final boolean save;
    private final ConcurrentHashMap<MemoryRange, RCObject> bufferMapping = new ConcurrentHashMap<>();

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource) {
        this(sharedMappedResource, true);
    }

    public SharedMappedBuffer(final SharedMappedResource sharedMappedResource, final boolean save) {
        this.sharedMappedResource = sharedMappedResource;
        this.save = save;
    }

    @Override
    public final MappedByteBuffer map(final MemoryRange memoryRange) {
        RCObject rcObject = bufferMapping.get(memoryRange);

        if (rcObject == null) {
            synchronized (this) {
                final MappedByteBuffer mapping = sharedMappedResource.map(memoryRange);
                rcObject = RCObject.getInstance(mapping, () -> {
                    if (save)
                        IOUtils.saveAndUnmap(mapping);
                    else
                        IOUtils.discardAndUnmap(mapping);
                });
                bufferMapping.put(memoryRange, rcObject);
            }
        }

        return (MappedByteBuffer) rcObject.get();
    }

    @Override
    public final boolean unmap(final MemoryRange memoryRange) {
        RCObject rcObject = bufferMapping.get(memoryRange);

        if (rcObject != null) {
            rcObject.release();

            return true;
        }

        return false;
    }


    @Override
    public final void close() throws IOException {
        sharedMappedResource.close();
    }
}
