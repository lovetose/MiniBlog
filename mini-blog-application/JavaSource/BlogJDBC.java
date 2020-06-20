import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class BlogJDBC {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = LogManager.getLogger(BlogJDBC.class);
	private final String tableName = "BLOG";
	private final String tableKey = "BLOG_ID";

	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public BlogModel getActiveBlog(){ //for showing in mainmenu
		StringBuffer sql = new StringBuffer();
		sql.append( "SELECT * FROM BLOG WHERE STATUS = 'ACTIVE'");	
		return jdbcTemplate.queryForObject(sql.toString(),BlogModel.class);
	}

	public int editBlog(BlogModel blog) {
		
		List<Object> params = new LinkedList<Object>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE " + tableName + " ");
		sql.append("SET ");

		sql.append("NAME = ?, ");
		sql.append("STATUS = ?, ");
		sql.append("CONTENT = ?, ");
		sql.append("CATEGORY = ?, ");
		sql.append("WHERE ATUHOR = ? AND BLOG_ID = ?");
		
		params.add(blog.getName());
		params.add(blog.getStatus());
		params.add(blog.getContent());
		params.add(blog.getCategory());
		params.add(blog.getAuthor());
		params.add(blog.getBlogId());

		int result = -1;
		result = jdbcTemplate.update(sql.toString(), params.toArray());
		if(result < 1){return -1;}
		else return result;
	}
	
	public int deleteBlog(String blogId, String author) {
 		int tmp = -1;
		StringBuffer sql = new StringBuffer();
		try{
		sql.append("DELETE FROM BLOG ");
		sql.append("WHERE BLOG_ID = '" + blogId + "' AND AUTHOR = '" + author + "'");
		
		tmp = jdbcTemplate.update(sql.toString());
			}catch(Exception e){
				logger.error("SQL Exception", e);
			}
		return tmp;
	}
}
