/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fanwe.library.cache;

import android.content.Context;

import java.io.File;

public abstract class ASDDisk implements ISDDisk
{
    private static Context mContext;
    private static IObjectConverter sGlobalObjectConverter;
    private static IEncryptConverter sGlobalEncryptConverter;

    private File mDirectory;
    private IObjectConverter mObjectConverter;
    private IEncryptConverter mEncryptConverter;

    protected ASDDisk(File directory)
    {
        if (directory == null)
        {
            throw new NullPointerException("directory file is null");
        }

        mDirectory = directory;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context)
    {
        mContext = context.getApplicationContext();
    }

    /**
     * 设置全局对象转换器
     *
     * @param globalObjectConverter
     */
    public static void setGlobalObjectConverter(IObjectConverter globalObjectConverter)
    {
        sGlobalObjectConverter = globalObjectConverter;
    }

    /**
     * 设置全局加解密转换器
     *
     * @param globalEncryptConverter
     */
    public static void setGlobalEncryptConverter(IEncryptConverter globalEncryptConverter)
    {
        sGlobalEncryptConverter = globalEncryptConverter;
    }

    @Override
    public ASDDisk setObjectConverter(IObjectConverter objectConverter)
    {
        mObjectConverter = objectConverter;
        return this;
    }

    @Override
    public ASDDisk setEncryptConverter(IEncryptConverter encryptConverter)
    {
        mEncryptConverter = encryptConverter;
        return this;
    }

    @Override
    public long size()
    {
        return mDirectory.length();
    }

    @Override
    public void delete()
    {
        Utils.deleteFileOrDir(mDirectory);
    }

    protected IObjectConverter getObjectConverter()
    {
        if (mObjectConverter != null)
        {
            return mObjectConverter;
        } else
        {
            return sGlobalObjectConverter;
        }
    }

    protected IEncryptConverter getEncryptConverter()
    {
        if (mEncryptConverter != null)
        {
            return mEncryptConverter;
        } else
        {
            return sGlobalEncryptConverter;
        }
    }

    //---------- util method start ----------

    private static Context getContext()
    {
        return mContext;
    }

    protected void checkObjectConverter()
    {
        if (getObjectConverter() == null)
        {
            throw new NullPointerException("you must provide an IObjectConverter instance before this");
        }
    }

    protected void checkEncryptConverter(boolean encrypt)
    {
        if (encrypt && getEncryptConverter() == null)
        {
            throw new NullPointerException("you must provide an IEncryptConverter instance before this");
        }
    }

    private static void checkContext()
    {
        if (mContext == null)
        {
            throw new NullPointerException("you must invoke init() method before this");
        }
    }

    protected static File getFileDir(String dirName)
    {
        checkContext();
        File dir = getContext().getExternalFilesDir(dirName);
        return dir;
    }

    protected static File getCacheDir(String dirName)
    {
        checkContext();
        File dir = new File(getContext().getExternalCacheDir(), dirName);
        return dir;
    }


    //---------- util method end ----------
}
