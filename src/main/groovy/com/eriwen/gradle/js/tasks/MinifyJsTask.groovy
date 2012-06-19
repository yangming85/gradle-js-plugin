/**
 * Copyright 2012 Eric Wendelin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.eriwen.gradle.js.tasks


import com.eriwen.gradle.js.JsMinifier
import org.gradle.api.GradleException
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction

class MinifyJsTask extends SourceTask {
    private static final JsMinifier MINIFIER = new JsMinifier()

    @OutputFile
    File dest

    @TaskAction
    def run() {
        if (source.files.size() != 1) {
            throw new GradleException("Only 1 file can be minified. Please run MinifyJs for each file.")
        }

        MINIFIER.minifyJsFile((source.files.toArray() as File[])[0], dest, project.closure.compilerOptions, project.closure.warningLevel, project.closure.compilationLevel)
    }
}