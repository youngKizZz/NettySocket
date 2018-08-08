package cn.turing.Mapper;

import cn.turing.bean.Air;
import cn.turing.bean.Group;


public interface DataMapper {
	/**
	 * 插入红外传感解析数据
	 * @param group 红外传感信息
	 * @return 返回受影响的行数
	 */
	Integer insertGroup(Group group);
	/**
	 * 插入空气检测数据
	 * @param air 空气检测信息
	 * @return 返回受影响的行数
	 */
	Integer insertAirAndPms200C(Air air);
}
