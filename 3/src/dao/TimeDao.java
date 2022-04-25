package dao;

public class TimeDao extends Dao{
	
	public static  TimeDao timeDao = new TimeDao();
		
	public int gettime(String city) {
		try {
			String sql = "select gmt from time where city=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, city);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {}
		return 0;
	}
}
