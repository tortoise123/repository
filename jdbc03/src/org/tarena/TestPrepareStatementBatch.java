package org.tarena;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.tarena.util.DbUtil;

/**
 * 基于PreparedStatement+批处理实现
 * @author Administrator
 *
 */
public class TestPrepareStatementBatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.openConnection();
			String sql = "insert into foo_1 (c1,c2) " +
					"values (?,?)";
			pst = con.prepareStatement(sql);
			for(int i=0;i<2000;i++){
				pst.setString(1, i+"");
				pst.setString(2, "larry"+i);
				pst.addBatch();//将上面一组参数加入批中
				if(i%50==0 && i != 0){
					pst.executeBatch();//够50个执行
				}
			}
			pst.executeBatch();//执行最后一批
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DbUtil.closeConnection(con, pst);
		}

	}

}
