import com.qrsx.shop.service.DaoService;
import com.qrsx.shop.service.MainMethods;
import com.qrsx.shop.service.ShopRunTimeException;
import com.qrsx.shop.utils.CheckAll;
import com.qrsx.shop.vo.User;

public class ShopMain {

	private static User user = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int select = 0;
		while (true) {
			MainMethods.showMume();
			select = getSelect();
			switch (select) {
			case 6:
				System.out.println("成功退出系统，欢迎下次使用！");
				return;

			case 1:
				toSign();
				break;

			case 2:
				tologin();
				break;

			case 3:
				toBuyGoods();
				break;

			case 4:
				showBuyGoods();
				break;

			case 5:
				toadmin();
				break;

			default:
				System.out.println("你输入的整数超出范围，请重新输入！！！");
				break;
			}
		}

	}

	/**
	 * 查看商城方法
	 */
	private static void toBuyGoods() {
		int select;
		System.out.println("你选择的菜单是：显示商品");
		DaoService.showGoodList();
		if (user == null) {
			System.out.println("你还没有登录，还不能购买商品");
			System.out.println("请选择菜单\t0、退出  1、登录 2、注册 ");
			select = getSelect();
			if (select == 1) {
				tologin();
			} else if (select == 2) {
				toSign();
				return;
			} else {
				return;
			}
		}
		if (user == null) {
			System.out.println("你还没有选择登录，请登录再购买商品！");
			return;
		}
		String flay = MainMethods.getScannerToString("是否购买商品（y/n）：").toLowerCase();
		while ("y".equals(flay)) {
			System.out.println("请输入你需要购买商品的ID:");
			select = getSelect();
			while (!DaoService.showGoodsById(select)) {
				System.out.print("请更改商品ID:");
				select = getSelect();
			}
			System.out.println("请输入要购买的商品的数量：");
			int count = getSelect();
			while (DaoService.isOverCountGoods(select, count)) {
				System.out.println("你购买的数量超出货存或小于等于0，请重新输入购买量：");
				count = getSelect();
			}
			// 修改数据，保存购买记录
			if (DaoService.updateAll(user, select, count)) {
				System.out.println("成功购买该商品！");
			} else {
				System.out.println("发生未知错误！");
			}
			flay = MainMethods.getScannerToString("是否继续购买（y/n）：").toLowerCase();
		}
	}

	/**
	 * 显示已经购买的商品
	 */

	private static void showBuyGoods() {
		if (user == null) {
			System.out.println("你还没有登录，请登录后在查看购物车！");
			return;
		}
		System.out.println("你选择的是：查看购物车：");
		DaoService.showAllBuy(user.getId());
	}

	/*
	 * 管理员登录模块
	 */
	private static void toadmin() {

		if (user == null || !"admin".equals(user.getName())) {
			adminLogin();
			if (user == null || !"admin".equals(user.getName())) {
				System.out.println("你还没有登录管理员帐号，请重新选择登录！");
				return;
			}
			int lx = 13;
			for (int i = 0; i < lx; i++)
				System.out.print("*");
			System.out.print("管理员登录成功");
			for (int i = 0; i < lx; i++)
				System.out.print("*");
			System.out.println();
		}
		if (user == null || !"admin".equals(user.getName())) {
			System.out.println("你还没有登录管理员帐号，请重新选择登录！");
			return;
		}
		System.out.println("你已有管理权限，不需要再次登录！");
		String name;
		int sel = 0;
		while (true) {
			MainMethods.showAdminMume();
			sel = getSelect();
			switch (sel) {
			case 1:
				String flay = "y";
				String gname;
				while ("y".equals(flay)) {
					gname = MainMethods.getScannerToString("请输入商品ID或名称，输入ID直接修改商品数量：\n");
					try {
						int id = Integer.parseInt(gname);
						if (DaoService.showGoodsById(id)) {
							System.out.println("请输入要修改商品的总数量：");
							int count = getSelect2();
							if (DaoService.updateCount(id, count)) {
								System.out.println("修改商品的总数量成功！");
								flay = MainMethods.getScannerToString("是否继续添加商品（y/n）：\n").toLowerCase();
								continue;
							}
						} else {
							System.out.println("不存在该ID的商品，但可以用来做商品的名字！");
						}
					} catch (Exception e) {

					}
					System.out.println("请输入商品价格：");
					int price = getSelect2();
					while (price < 0) {
						System.out.println("商品价格不能小于0！，请重新输入！");
						price = getSelect2();
					}
					System.out.println("请输入商品数量：");
					int count = getSelect2();
					while (count < 0) {
						System.out.println("商品总数不能小于0！，请重新输入！");
						count = getSelect2();
					}
					if (DaoService.addShop(gname, price, count)) {
						System.out.println("添加商品成功！");
					} else {
						System.out.println("发生未知错误，后台维护中...");
						break;
					}
					flay = MainMethods.getScannerToString("是否继续添加商品（y/n）：\n").toLowerCase();
				}
				break;
			case 2:
				System.out.println("请输入要修改的ID编号：");
				int pid = getSelect2();
				if (DaoService.showGoodsById(pid)) {
					gname = MainMethods.getScannerToString("请输入商品名称：\n");
					System.out.println("请输入商品价格：");
					int price = getSelect2();
					while (price < 0) {
						System.out.println("商品价格不能小于0！，请重新输入！");
						price = getSelect2();
					}
					System.out.println("请输入商品数量：");
					int count = getSelect2();
					while (count < 0) {
						System.out.println("商品总数不能小于0！，请重新输入！");
						count = getSelect2();
					}
					if (DaoService.updeGoods(pid, gname, price, count)) {
						System.out.println("成功修改商品参数");
						break;
					}
					System.out.println("发生未知错误...");
				}
				break;

			case 3:
				System.out.println("请输入要删除商品的ID:");
				int id = getSelect2();
				if (!DaoService.delGoods(id)) {
					System.out.println("删除失败，不存在该商品！");
					break;
				}
				System.out.println("删除成功！");
				break;
			case 4:
				DaoService.showGoodList();
				break;
			case 5:
				System.out.println("请输入要查询商品的名字");
				name = MainMethods.getScannerToString("");
				if (DaoService.showGoodsByName(name))
					;
				break;
			case 6:
				System.out.println("你已经成功退出管理员，请重新登录！");
				user = null;
				return;

			default:
				System.out.println("你输入的整数超出范围，请重新输入！！！");
				break;
			}
		}
	}

	private static void adminLogin() {
		System.out.println("你选择的菜单是：管理员登录");
		String name = MainMethods.getScannerToString("请输入管理员帐号：\n");
		String pwd = MainMethods.getScannerToString("请输入管理员密码：\n");
		if (!name.equals("admin")) {
			System.out.println("不存在该管理员，请重新选择！");
			return;
		}
		if (!DaoService.isExitUser(name)) {
			System.out.println("不存在该管理员，请重新选择！");
			return;
		}

		if ((user = DaoService.tologin(name, pwd)) == null) {
			System.out.println("你输入的帐号或密码错误，请重新选择登录...\n");
			return;
		}
	}

	/**
	 * 用户登录模块
	 */
	private static void tologin() {
		if (user != null) {
			System.out.println("你已经登录本系统，不需要再次登录！");
			return;
		}
		System.out.println("你选择的菜单是：用户登录");
		String name = MainMethods.getScannerToString("请输入登录用户名：\n");
		String pwd = MainMethods.getScannerToString("请输入登录密码：\n");
		if (!DaoService.isExitUser(name)) {
			System.out.println("用户名不存在，请重新选择登录...");
			return;
		}

		if ((user = DaoService.tologin(name, pwd)) == null) {
			System.out.println("你输入的用户名或密码错误，请重新选择登录...\n");
			return;
		}
		System.out.println("登录成功，欢迎使用...");
	}

	/**
	 * 用户注册方法
	 */
	private static void toSign() {
		if (user != null) {
			System.out.println("你已经登录，不要重复注册！");
			return;
		}
		System.out.println("你选择的菜单是：注册\n欢迎注册...");
		String name = MainMethods.getScannerToString("请输入用户名：\n");
		while (DaoService.isExitUser(name)) {
			System.out.println("您输入的用户名已经被使用，请重新输入：");
			name = MainMethods.getScannerToString("请输入用户名：\n");
		}
		while (!CheckAll.isUsername(name)) {
			System.out.println("您输入的用户名不合法，必须是字母数字，下划线组成！，不少于3位,请重新输入：");
			name = MainMethods.getScannerToString("请输入用户名：\n");
		}
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,16}$";

		String pwd = MainMethods.getScannerToString("请输入密码：\n");
		while (!pwd.matches(regex)) {
			System.out.println("您输入的密码，必须含有字母数字，不少于3位,请重新输入：");
			pwd = MainMethods.getScannerToString("请输入密码：\n");
		}

		String pwd2 = MainMethods.getScannerToString("请确认密码：\n");
		while (!pwd.equals(pwd2)) {
			System.out.println("你输入的两次密码不相同，请再次输入！");
			pwd = MainMethods.getScannerToString("请输入密码：\n");
			while (!pwd.matches(regex)) {
				System.out.println("您输入的密码，必须含有字母数字，不少于3位,请重新输入：");
				pwd = MainMethods.getScannerToString("请输入密码：\n");
			}
			pwd2 = MainMethods.getScannerToString("请确认密码：\n");
		}
		if (DaoService.addUser(name, pwd2)) {
			System.out.println("注册成功,欢迎继续使用！");
		} else {
			System.out.println("发生未知错误，管理员处理中...");
		}
	}

	/**
	 * 得到用户输入的选项号，数字
	 * 
	 * @return
	 */
	private static int getSelect() {
		int select;
		try {
			select = MainMethods.getScannerToInt("");
		} catch (ShopRunTimeException e) {
			System.out.println("你输入的数据类型不对，请重新选择");
			MainMethods.showMume();
			return getSelect();
		}
		return select;
	}

	/**
	 * 管理员数据输入
	 * 
	 * @return
	 */
	private static int getSelect2() {
		int select;
		try {
			select = MainMethods.getScannerToInt("");
		} catch (ShopRunTimeException e) {
			System.out.println("你输入的数据类型不对，请重新输入：");
			return getSelect2();
		}
		return select;
	}
}
