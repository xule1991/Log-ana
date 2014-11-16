package com.xule.mapper;

import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/16/14
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Mapper {
    public abstract Object map(Matcher matcher);
}
