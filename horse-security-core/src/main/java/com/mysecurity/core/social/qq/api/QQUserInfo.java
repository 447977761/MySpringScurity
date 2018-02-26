/**
 * 
 */
package com.mysecurity.core.social.qq.api;

/**
 * @author Liziyue
 *
 */
public class QQUserInfo {
	/**
	 * 	������
	 */
	private String ret;
	/**
	 * ���ret<0��������Ӧ�Ĵ�����Ϣ��ʾ����������ȫ����UTF-8���롣
	 */
	private String msg;
	/**
	 * 
	 */
	private String openId;
	/**
	 * ��֪��ʲô�������ĵ���ûд������ʵ��api�������С�
	 */
	private String is_lost;
	/**
	 * ʡ(ֱϽ��)
	 */
	private String province;
	/**
	 * ��(ֱϽ����)
	 */
	private String city;
	/**
	 * ��������
	 */
	private String year;
	/**
	 * 	�û���QQ�ռ���ǳơ�
	 */
	private String nickname;
	/**
	 * 	��СΪ30��30���ص�QQ�ռ�ͷ��URL��
	 */
	private String figureurl;
	/**
	 * 	��СΪ50��50���ص�QQ�ռ�ͷ��URL��
	 */
	private String figureurl_1;
	/**
	 * 	��СΪ100��100���ص�QQ�ռ�ͷ��URL��
	 */
	private String figureurl_2;
	/**
	 * 	��СΪ40��40���ص�QQͷ��URL��
	 */
	private String figureurl_qq_1;
	/**
	 * 	��СΪ100��100���ص�QQͷ��URL����Ҫע�⣬�������е��û���ӵ��QQ��100��100��ͷ�񣬵�40��40��������һ�����С�
	 */
	private String figureurl_qq_2;
	/**
	 * 	�Ա� �����ȡ������Ĭ�Ϸ��ء��С�
	 */
	private String gender;
	/**
	 * 	��ʶ�û��Ƿ�Ϊ�����û���0�����ǣ�1���ǣ���
	 */
	private String is_yellow_vip;
	/**
	 * 	��ʶ�û��Ƿ�Ϊ�����û���0�����ǣ�1���ǣ�
	 */
	private String vip;
	/**
	 * 	����ȼ�
	 */
	private String yellow_vip_level;
	/**
	 * 	����ȼ�
	 */
	private String level;
	/**
	 * ��ʶ�Ƿ�Ϊ��ѻ����û���0�����ǣ� 1���ǣ�
	 */
	private String is_yellow_year_vip;
	
	
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getIs_lost() {
		return is_lost;
	}
	public void setIs_lost(String is_lost) {
		this.is_lost = is_lost;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFigureurl() {
		return figureurl;
	}
	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}
	public String getFigureurl_1() {
		return figureurl_1;
	}
	public void setFigureurl_1(String figureurl_1) {
		this.figureurl_1 = figureurl_1;
	}
	public String getFigureurl_2() {
		return figureurl_2;
	}
	public void setFigureurl_2(String figureurl_2) {
		this.figureurl_2 = figureurl_2;
	}
	public String getFigureurl_qq_1() {
		return figureurl_qq_1;
	}
	public void setFigureurl_qq_1(String figureurl_qq_1) {
		this.figureurl_qq_1 = figureurl_qq_1;
	}
	public String getFigureurl_qq_2() {
		return figureurl_qq_2;
	}
	public void setFigureurl_qq_2(String figureurl_qq_2) {
		this.figureurl_qq_2 = figureurl_qq_2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIs_yellow_vip() {
		return is_yellow_vip;
	}
	public void setIs_yellow_vip(String is_yellow_vip) {
		this.is_yellow_vip = is_yellow_vip;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getYellow_vip_level() {
		return yellow_vip_level;
	}
	public void setYellow_vip_level(String yellow_vip_level) {
		this.yellow_vip_level = yellow_vip_level;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getIs_yellow_year_vip() {
		return is_yellow_year_vip;
	}
	public void setIs_yellow_year_vip(String is_yellow_year_vip) {
		this.is_yellow_year_vip = is_yellow_year_vip;
	}
}
