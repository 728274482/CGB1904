package com.company.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sys.dao.SysMenuDao;
import com.company.sys.dao.SysRoleMenuDao;
import com.company.sys.dao.SysUserDao;
import com.company.sys.dao.SysUserRoleDao;
import com.company.sys.entity.SysUser;
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	
	@Autowired
	private SysMenuDao sysMenuDao;


	
	/**
	   * 设置加密算法及加密次数等
	   * 设置凭证匹配器(与用户添加操作使用相同的加密算法)
	 * */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher hcm = 
		new HashedCredentialsMatcher();
		//设置加密算法
		hcm.setHashAlgorithmName("MD5");
		//设置加密次数
		hcm.setHashIterations(1);
		super.setCredentialsMatcher(hcm);
	}
	
	/**负责授权信息的获取及封装*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.获取登录用户信息，例如用户id
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		Integer userId=user.getId();
		//2.基于用户id获取用户拥有的角色(sys_user_roles)
		List<Integer> roleIds = 
		sysUserRoleDao.findRoleIdsByUserId(userId);
		if(roleIds==null||roleIds.size()==0)
			throw new AuthenticationException();
		//3.基于角色id获取菜单id(sys_role_menus)
		Integer[] array= {};
		List<Integer> menuIds = 
		sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		
		if(menuIds==null||menuIds.size()==0)
			throw new AuthenticationException();
		//4.基于菜单id获取权限标识(sys_menus)
		List<String> permissions = 
		sysMenuDao.findPermissions(menuIds.toArray(array));
		if(permissions==null||permissions.size()==0)
			throw new AuthenticationException();
		//5.对权限标识信息进行封装并返回
		Set<String> set=new HashSet<>();
		for (String per :permissions) {
			if(!StringUtils.isEmpty(per)) {
				set.add(per);
			}
		}
		
		SimpleAuthorizationInfo info = 
		new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		
		//返回给授权管理器
		return info;
	}
	
	/**负责认证信息的获取及封装
	 * @param token 包含用户名和密码
	 * 
	   * 通过此方法完成认证数据的获取及封装,系统
	   * 底层会将认证数据传递认证管理器，由认证
	   * 管理器完成认证操作。
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		//1.获取用户名(用户页面输出)
		UsernamePasswordToken upToken=
		(UsernamePasswordToken)token;
		String username=upToken.getUsername();
		
		//2.基于用户名查询用户信息
		SysUser user = sysUserDao.findUserByUserName(username);
		
		//3.判定用户是否存在
		if(user==null)
		throw new UnknownAccountException();
		
		//4.判断用户是否已被禁用
		if(user.getValid()==0)
		throw new LockedAccountException();
		
		//5.封装用户信息
		ByteSource credentialsSalt = 
		ByteSource.Util.bytes(user.getSalt());
		
		SimpleAuthenticationInfo info = 
		new SimpleAuthenticationInfo(
				user,//principal用户身份
				user.getPassword(),//hashedCredentials 已加密的密码
				credentialsSalt,
				getName() //返回给SecurityManager(本身认证和授权功能)
				);
		/**6.返回封装结果
		   * 返回值会传递给认证管理器(后续认证管理器会通过此信息完成认证操作)
		 */
		return info;		
	}
	
}
		
		
