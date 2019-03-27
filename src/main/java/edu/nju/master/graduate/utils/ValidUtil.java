package edu.nju.master.graduate.utils;

import edu.nju.master.graduate.exception.BusinessException;
import org.springframework.validation.BindingResult;

public class ValidUtil {
	/**
	 * 使用validate框架，如果校验不通过组装错误数据，抛出异常统一处理。
	 * @param bindingResult
	 */
	public static void judgeResult(BindingResult bindingResult) {
		if(bindingResult!=null && bindingResult.hasErrors()) {
			/*StringBuffer sb = new StringBuffer();
			List<ObjectError> ls=bindingResult.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                sb.append(ls.get(i).getDefaultMessage()).append(",");
            }
            throw new BusinessException("1", sb.toString());*/
			throw new BusinessException(DictionaryConst.RESULT_CODE.VALIDA_ERROR, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
	}

}
