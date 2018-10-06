/*******************************************************************************
 * Copyright (C) 2018 Joao Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.wrapper.spotify.dump;

import com.google.common.util.concurrent.FutureCallback;

import javax.annotation.Nullable;
import java.util.Objects;

public final class MongoOperationHandler implements FutureCallback<Integer> {

    public static FutureCallback<Integer> create() {
        return new MongoOperationHandler();
    }

    private MongoOperationHandler() {
    }

    @Override
    public void onSuccess(@Nullable final Integer result) {
        if (Objects.isNull(result) || (result != 1)) {
            throw new IllegalArgumentException("Operation on object failed: " + result);
        }
    }

    @Override
    public void onFailure(final Throwable t) {
        throw new IllegalArgumentException("Operation failed: ", t);
    }

}
