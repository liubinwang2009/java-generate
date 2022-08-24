package ${package}.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ${comment}
 * @author liubinwang
 *
 */
 @Table(name = "${tableName}")
public class ${className} implements java.io.Serializable{
    /** 版本号 */
    private static final long serialVersionUID = 1L;	
	${feilds}
	
}
