package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Product_Order;

public class ProductOrderImpl implements ProductOrderDAO {
	
	private  Connection conn;

	public ProductOrderImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean saveOrder(List<Product_Order> blist) {
		boolean f=false;
		try {
			
			String sql="insert into product_order(order_id,user_name,email,address,phone,product_name,price,payment) values(?,?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			PreparedStatement ps= conn.prepareStatement(sql);
			
			for(Product_Order b:blist) 
			{
				ps.setString(1, b.getOrderId());
				ps.setString(2, b.getUserName());
				ps.setString(3, b.getEmail());
				ps.setString(4, b.getFulladd());
				ps.setString(5, b.getPhoneNo());
				ps.setString(6, b.getProductName());
				ps.setString(7, b.getPrice());
				ps.setString(8, b.getPaymentType());
				ps.addBatch();
			}
			
			int[] count=ps.executeBatch();
			conn.commit();
			f=true;
			conn.setAutoCommit(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<Product_Order> getProduct(String email) {
		List<Product_Order> list=new ArrayList<Product_Order>();
		Product_Order o=null;
		
		 try {
			 
			 String sql="select * from product_order where email=?";
			 PreparedStatement ps= conn.prepareStatement(sql);
			 ps.setString(1, email);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 o=new Product_Order();
				 o.setId(rs.getInt(1));
				 o.setOrderId(rs.getString(2));
				 o.setUserName(rs.getString(3));
				 o.setEmail(rs.getString(4));
				 o.setFulladd(rs.getString(5));
				 o.setPhoneNo(rs.getString(6));
				 o.setProductName(rs.getString(7));
				 o.setPrice(rs.getString(8));
				 o.setPaymentType(rs.getString(9));
				 list.add(0, o);
			 } 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		return list;
	}

	@Override
	public List<Product_Order> getAllOrders() {
		List<Product_Order> list=new ArrayList<Product_Order>();
		Product_Order o=null;
		
		 try {
			 
			 String sql="select * from product_order";
			 PreparedStatement ps= conn.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 o=new Product_Order();
				 o.setId(rs.getInt(1));
				 o.setOrderId(rs.getString(2));
				 o.setUserName(rs.getString(3));
				 o.setEmail(rs.getString(4));
				 o.setFulladd(rs.getString(5));
				 o.setPhoneNo(rs.getString(6));
				 o.setProductName(rs.getString(7));
				 o.setPrice(rs.getString(8));
				 o.setPaymentType(rs.getString(9));
				 list.add(0, o);
			 } 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		return list;
	}

	
	}
	
	

