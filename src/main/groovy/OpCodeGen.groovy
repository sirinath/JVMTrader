import org.jetbrains.annotations.Nullable

/*
 * Copyright (c) 2015. Suminda Sirinath Salpitikorala Dharmasena
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

String upcase1st(String str) {
    if (str == null || str.length() == 0)
        return ""

    return str.substring(0, 1).toUpperCase() + str.substring(1)
}

String genUnaryOp(Class<?> type, String opType) {
    String typeName = type.isPrimitive() ? type.getSimpleName() : "T"
    String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : type.isEnum() ? "Enum" : "Object"
    String generic = type.isPrimitive() ? "" : "<T>"

    StringBuilder buffer = new StringBuilder("""
/*
 * Copyright (c) 2015. Suminda Sirinath Salpitikorala Dharmasena
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package com.susico.utils.functions;

import org.jetbrains.annotations.*;

@FunctionalInterface
public interface ${opType}${typeSuffix}${generic} {
    @Nullable $typeName apply(${
        opType.startsWith("UnaryOp") ? "@NotNull final $typeName x" : opType.startsWith("BiOp") ? "@NotNull final $typeName x, @NotNull final $typeName y" : "@NotNull final $typeName x, @NotNull final $typeName ... values"
    });
}
""")
}

void genUnaryOpGen() {
    String[] opTypes = ["BiOp", "UnaryOp", "MultiOp"]
    Class<?>[] types = [Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class, Enum.class]

    File p = new File(".\\..\\java\\com\\susico\\utils\\functions\\")
    p.mkdirs()

    for (String opType : opTypes) {
        for (Class<?> type : types) {
            String typeSuffix = type.isPrimitive() ? upcase1st(type.getSimpleName()) : type.isEnum() ? "Enum" : "Object"

            File f = new File(p, "${opType}${typeSuffix}.java")
            f.createNewFile()

            PrintWriter pw = f.newPrintWriter()
            pw.print(genUnaryOp(type, opType))
            pw.flush()
            pw.close()
        }
    }
}

genUnaryOpGen()