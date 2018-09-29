package com.sd.lib.cache.handler;

import com.sd.lib.cache.DiskInfo;

/**
 * Boolean处理类
 */
public class BooleanHandler extends ByteConverterHandler<Boolean>
{
    public BooleanHandler(DiskInfo diskInfo)
    {
        super(diskInfo);
    }

    @Override
    protected byte[] valueToByte(Boolean value)
    {
        return value.toString().getBytes();
    }

    @Override
    protected Boolean byteToValue(byte[] bytes, Class clazz)
    {
        return Boolean.valueOf(new String(bytes));
    }

    @Override
    protected String getKeyPrefix()
    {
        return "boolean_";
    }
}