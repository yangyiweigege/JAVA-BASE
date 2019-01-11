package com.weige.tree.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONObject;

//import com.alibaba.fastjson.JSONObject;
/**
 * <pre>
 * 功       能:树节点 
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2018年3月9日 下午3:35:20
 * Q    Q: 2873824885
 * </pre>
 */
public class Tree<T extends BaseTree> {
	/**
	 * 父节点
	 */
	private T parent;

	/**
	 * 子节点
	 */
	private List<Tree<T>> child = new ArrayList<Tree<T>>();

	public Tree() {

	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public List<Tree<T>> getChild() {
		return child;
	}

	public void setChild(List<Tree<T>> child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Tree [parent=" + parent + ", child=" + child + "]";
	}

	public static void main(String[] args) {
		Tree<MeetArea> trees = new Tree<MeetArea>();
		// 先查询第一级树
		Connection connection = Mysql.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			// ResultSet resultSet = statement.executeQuery("select * from
			// t_area where tid='c023c2f6-c3a4-46c1-b084-bcd054737789' ");
			ResultSet resultSet = statement.executeQuery("select * from t_area where parent_code = '0' ");
			while (resultSet.next()) {
				Tree<MeetArea> tree = new Tree<MeetArea>();
				MeetArea meetArea = new MeetArea();
				meetArea.setAreaName(resultSet.getString("area_name"));
				meetArea.setTid(resultSet.getString("tid"));
				meetArea.setParentCode(resultSet.getString("parent_code"));
				tree.setParent(meetArea);
				trees.getChild().add(tree);
			}
			// 递归形成二级树
			for (Tree<MeetArea> meetTree : trees.getChild()) {
				generateTree(meetTree);
			}
			MeetArea parentMeetArea = new MeetArea();
			parentMeetArea.setTid("0");
			parentMeetArea.setAreaName("虚拟节点");
			trees.setParent(parentMeetArea);

			readTree(trees);

			// FontTree fontTree = new FontTree(); fontTree.setTitle("根节点");
			// fontTree.setExpand(true); fontTree.setId(null);

			// convertTree(trees, fontTree);
			// System.out.println(JSONObject.toJSONString(fontTree));
			// System.out.println(JSONObject.toJSONString(trees));
			// 查找指定节点

			System.out.println("=========查找指定节点==========");
			Tree<MeetArea> result = getPointTree("c023c2f6-c3a4-46c1-b084-bcd054737789", trees);
			if (result != null) {
				readTree(result);
			}
			System.out.println("=========查询指定节点前缀==========");
			String allAreaName = getAreaName("88e5cb6a-7d50-47c0-a6fb-4bae03d12e97");
			System.out.println(allAreaName);
			System.out.println("=========查询所有叶子节点==========");
			List<MeetArea> meetAreas = new ArrayList<>();
			getLastLevel("0", meetAreas);
			System.out.println(meetAreas.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 功       能: 递归形成多级树
	 * 涉及版本: V3.0.0 
	 * 创  建  者: yangyiwei
	 * 日       期: 2018年3月9日 下午2:54:49
	 * Q    Q: 2873824885
	 * </pre>
	 */
	public static void generateTree(Tree<MeetArea> tree) {
		Connection connection = Mysql.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("select * from t_area where parent_code ='" + tree.getParent().getTid() + "' ");
			while (resultSet.next()) {
				Tree<MeetArea> sonTree = new Tree<MeetArea>();
				MeetArea meetArea = new MeetArea();
				meetArea.setAreaName(resultSet.getString("area_name"));
				meetArea.setTid(resultSet.getString("tid"));
				meetArea.setParentCode(resultSet.getString("parent_code"));
				sonTree.setParent(meetArea);
				tree.getChild().add(sonTree);// 将树挂载到这个节点
				generateTree(sonTree);// 继续递归这个树
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从缓存树中获取叶子节点
	 */
	public static void getLastLevelFromMeta(String id, List<MeetArea> areas) throws Exception {
		Connection connection = Mysql.getConnection();
		Statement statement;
		statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from t_area where parent_code ='" + id + "' ");
		int flag = 0;
		while (resultSet.next()) {
			flag = 1;
			getLastLevel(resultSet.getString("tid"), areas);
		}
		// 如果没有子集 则叶子节点
		if (flag == 0) {
			// Statement statement2 = connection.createStatement();
			ResultSet resultSet2 = statement.executeQuery("select * from t_area where tid ='" + id + "' ");
			while (resultSet2.next()) {
				MeetArea meetArea = new MeetArea();
				meetArea.setAreaName(resultSet2.getString("area_name"));
				meetArea.setTid(resultSet2.getString("tid"));
				areas.add(meetArea);
			}

		}
	}

	/**
	 * 获取叶子节点
	 */
	public static void getLastLevel(String id, List<MeetArea> areas) throws Exception {
		Connection connection = Mysql.getConnection();
		Statement statement;
		statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from t_area where parent_code ='" + id + "' ");
		int flag = 0;
		while (resultSet.next()) {
			flag = 1;
			getLastLevel(resultSet.getString("tid"), areas);
		}
		// 如果没有子集 则叶子节点
		if (flag == 0) {
			// Statement statement2 = connection.createStatement();
			ResultSet resultSet2 = statement.executeQuery("select * from t_area where tid ='" + id + "' ");
			while (resultSet2.next()) {
				MeetArea meetArea = new MeetArea();
				meetArea.setAreaName(resultSet2.getString("area_name"));
				meetArea.setTid(resultSet2.getString("tid"));
				areas.add(meetArea);
			}

		}
	}

	/**
	 * 多层级查询
	 * 
	 * @return
	 */
	public static String getAreaName(String id) throws Exception {
		Connection connection = Mysql.getConnection();
		Statement statement;

		statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from t_area where tid ='" + id + "' ");
		while (resultSet.next()) {
			if (!resultSet.getString("parent_code").equals("0")) {
				return getAreaName(resultSet.getString("parent_code")) + "-" + resultSet.getString("area_name");
			} else {
				return resultSet.getString("area_name");
			}
		}
		return "";
	}

	/**
	 * <pre>
	 * 功       能: 递归读出这棵树
	 * 涉及版本: V3.0.0 
	 * 创  建  者: yangyiwei
	 * 日       期: 2018年3月9日 下午3:05:25
	 * Q    Q: 2873824885
	 * </pre>
	 */
	public static void readTree(Tree<MeetArea> tree) {
		System.out.println("区域:" + tree.getParent().getAreaName() + " tid: " + tree.getParent().getTid()
				+ " parentCode: " + tree.getParent().getParentCode());
		for (Tree<MeetArea> son : tree.getChild()) {
			readTree(son);
		}

	}

	/**
	 * <pre>
	 * 功       能: 递归读出部分树
	 * 涉及版本: V3.0.0 
	 * 创  建  者: yangyiwei
	 * 日       期: 2018年3月9日 下午3:05:25
	 * Q    Q: 2873824885
	 * </pre>
	 */
	public static Tree<MeetArea> getPointTree(String id, Tree<MeetArea> tree) {
		if (tree.getParent().getTid().equals(id)) { // 如果当前节点id 和传入的id一致，则返回该节点
			return tree;
		} else { // 如果当前节点不是查询节点，则继续递归
			for (Tree<MeetArea> son : tree.getChild()) {
				Tree<MeetArea> getNode = getPointTree(id, son);
				if (getNode != null) { // 如果查询到了 返回
					return getNode;
				}

			}
			return null;// 遍历到最后节点，不存在下一个节点，未找到 返回null
		}
	}

	/**
	 * <pre>
	 * 功       能: 转换这颗树
	 * 涉及版本: V3.0.0 
	 * 创  建  者: yangyiwei
	 * 日       期: 2018年3月9日 下午3:05:25
	 * Q    Q: 2873824885
	 * </pre>
	 */
	public static void convertTree(Tree<MeetArea> tree, FontTree fontTree) {

		for (Tree<MeetArea> son : tree.getChild()) {
			System.out.println("区域名: " + son.getParent().getAreaName());
			FontTree fontSon = new FontTree();
			fontSon.setId(son.getParent().getTid());
			fontSon.setTitle(son.getParent().getAreaName());
			fontSon.setExpand(true);
			fontTree.getChildren().add(fontSon);
			convertTree(son, fontSon);
		}

		if (fontTree.getChildren().size() == 0) {
			fontTree.setExpand(false);
		}

	}

	public void getT(Tree<T> tree) {
		tree.getParent().getName();
	}

}
