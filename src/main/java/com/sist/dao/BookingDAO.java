package com.sist.dao;

import java.io.*;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BookingVO;
import com.sist.vo.JjimVO;
import com.sist.vo.OrderVO;
import com.sist.vo.ReviewVO;
import com.sist.vo.UserVO;


public class BookingDAO {
	//1.XML파일 읽기
	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			System.out.println("====BookingDAO static error====");
			ex.printStackTrace();
		}
	}
	//<select id="bookingListData" resultType="BookingVO" parameterType="hashmap">
	public static List<BookingVO> bookingListData(Map map)
	{
		List<BookingVO> list=null;
		SqlSession session=null;
		try
		{ 
			session=ssf.openSession();
			list=session.selectList("bookingListData",map);
		}catch(Exception ex)
		{
			System.out.println("====BookingDAO.bookingListData() error====");
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
		return list;
	}
	/*
	 * public static int bookingTotalPage(String store) { int total=0; SqlSession
	 * session=null; try { session=ssf.openSession();
	 * total=session.selectOne("bookingTotalPage", store); }catch(Exception ex) {
	 * System.out.println("bookingTotalPage : error"); ex.printStackTrace(); }
	 * finally { if(session!=null) session.close(); // POOL => 반환 } return total; }
	 */
	
		public static int bookingTotalPage2(Map map)
	   {
		   int total=0;
		   SqlSession session=null;
		   try
		   {
			   session=ssf.openSession();
			   total=session.selectOne("bookingTotalPage2", map);
		   }catch(Exception ex)
		   {
			   System.out.println("bookingTotalPage2 : error");
			   ex.printStackTrace();
		   }
		   finally
		   {
			   if(session!=null)
				   session.close(); // POOL => 반환 
		   }
		   //System.out.println("totalDAO2 ============== : "+total);
		   return total;
	   }
	 
	 public static BookingVO hospitalDetailData(int o_no)
	   {
		    BookingVO vo=new BookingVO();
		    SqlSession session=null;
		    try
		    {
		    	session=ssf.openSession();
		    	vo=session.selectOne("hospitalDetailData", o_no);//row
		    }catch(Exception ex)
		    {
		    	System.out.println("hospitalDetailData: error");
		    	ex.printStackTrace();
		    }
		    finally
		    {
		    	if(session!=null)
		    		session.close();
		    }
		    return vo;
	   }
	 
	 public static BookingVO bookingDetailData(int o_no)
	   {
		    BookingVO vo=new BookingVO();
		    SqlSession session=null;
		    try
		    {
		    	session=ssf.openSession();
		    	vo=session.selectOne("bookingDetailData", o_no);//row
		    }catch(Exception ex)
		    {
		    	System.out.println("bookingDetailData: error");
		    	ex.printStackTrace();
		    }
		    finally
		    {
		    	if(session!=null)
		    		session.close();
		    }
		    return vo;
	   }
	 
	 public static void bookingJjimInsert(JjimVO vo)
	 {
		 SqlSession session=null;
		 try
		 {
			 session=ssf.openSession(true);
			 session.insert("bookingJjimInsert", vo);
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 if(session!=null)
				 session.close();
		 }
		 
	 }
	 
	 public static int bookingJjimCount(JjimVO vo)
	 {
		 int count=0;
		 SqlSession session=null;
		 try
		 {
			 session=ssf.openSession();
			 count=session.selectOne("bookingJjimCount", vo);
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 if(session!=null)
				 session.close();
		 }
		 return count;
	 }
	 /*
	  *  <delete id="bookingJjimDelete" parameterType="JjimVO">
		 	DELETE FROM ord_jjim_4
		 	WHERE o_no=#{o_no} AND id=#{id}
		 </delete>
	  */
	 public static void bookingJjimDelete(JjimVO vo)
	 {
		 SqlSession session=null;
		 try
		 {
			 session=ssf.openSession(true);
			 session.delete("bookingJjimDelete", vo);
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 if(session!=null)
				 session.close();
		 }
		 
	 }
	 /* 예약자정보 */
	 public static List<UserVO> bookingUserInfo(String id) {
			List<UserVO> list=null;
			SqlSession session=null;
			try
			{
				session=ssf.openSession();
				list=session.selectList("bookingUserInfo",id);
			} catch (Exception ex)
			{
				System.out.println("bookinguserInfo: error");
				ex.printStackTrace();
			} finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
	 /* 예약 */
	 public static void bookingInsert(OrderVO vo)
	   {
		   SqlSession session=null;
		   try
		   {
			   session=ssf.openSession(true);//autocommit
			   session.insert("bookingInsert",vo);
		   }catch(Exception ex)
		   {
			   System.out.println("bookingInsert: error");
			   ex.printStackTrace();
		   }
		   finally
		   {
			   if(session!=null)
				   session.close();
		   }
	   }
	 
	//리뷰 달기
	 public static void reviewInsert(ReviewVO vo)
	   {
		   SqlSession session=null;
		   try
		   {
			   session=ssf.openSession(true);
			   session.insert("reviewInsert",vo);
		   }catch(Exception ex)
		   {
			   System.out.println("reviewInsert: error");
			   ex.printStackTrace();
		   }
		   finally
		   {
			   if(session!=null)
				   session.close();
		   }
	   }
	 
	 /* 리뷰 리스트*/
     public static List<ReviewVO> reviewListData(int o_no) {
		List<ReviewVO> list=null;
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("reviewListData",o_no);
		} catch (Exception ex)
		{
			System.out.println("reviewListData: error");
			ex.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
}
