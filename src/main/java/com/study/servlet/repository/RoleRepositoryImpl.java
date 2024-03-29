package com.study.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.study.servlet.entity.Role;
import com.study.servlet.util.DBConnectionMgr;

public class RoleRepositoryImpl implements RoleRepository{

	private static RoleRepository instance;
	public static RoleRepository getInstance() {
		if(instance == null) {
			instance = new RoleRepositoryImpl();
		}
		return instance;
	}
	
	private DBConnectionMgr pool;
	
	public RoleRepositoryImpl() {
		pool = DBConnectionMgr.getInstance();
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Role role = null;
		
		try {
			con = pool.getConnection();
			String sql = "select role_id, role_name from role_mst where role_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roleName);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				role = Role.builder()
						.roleId(rs.getInt(1))
						.roleName(rs.getString(2))
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return role;
	}

}
