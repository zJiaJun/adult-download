package com.github.zjiajun.adult.process;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.PageResult;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public interface Process {

    PageResult process(Page page);

}
