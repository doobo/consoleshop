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
				System.out.println("�ɹ��˳�ϵͳ����ӭ�´�ʹ�ã�");
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
				System.out.println("�����������������Χ�����������룡����");
				break;
			}
		}

	}

	/**
	 * �鿴�̳Ƿ���
	 */
	private static void toBuyGoods() {
		int select;
		System.out.println("��ѡ��Ĳ˵��ǣ���ʾ��Ʒ");
		DaoService.showGoodList();
		if (user == null) {
			System.out.println("�㻹û�е�¼�������ܹ�����Ʒ");
			System.out.println("��ѡ��˵�\t0���˳�  1����¼ 2��ע�� ");
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
			System.out.println("�㻹û��ѡ���¼�����¼�ٹ�����Ʒ��");
			return;
		}
		String flay = MainMethods.getScannerToString("�Ƿ�����Ʒ��y/n����").toLowerCase();
		while ("y".equals(flay)) {
			System.out.println("����������Ҫ������Ʒ��ID:");
			select = getSelect();
			while (!DaoService.showGoodsById(select)) {
				System.out.print("�������ƷID:");
				select = getSelect();
			}
			System.out.println("������Ҫ�������Ʒ��������");
			int count = getSelect();
			while (DaoService.isOverCountGoods(select, count)) {
				System.out.println("�㹺����������������С�ڵ���0�����������빺������");
				count = getSelect();
			}
			// �޸����ݣ����湺���¼
			if (DaoService.updateAll(user, select, count)) {
				System.out.println("�ɹ��������Ʒ��");
			} else {
				System.out.println("����δ֪����");
			}
			flay = MainMethods.getScannerToString("�Ƿ��������y/n����").toLowerCase();
		}
	}

	/**
	 * ��ʾ�Ѿ��������Ʒ
	 */

	private static void showBuyGoods() {
		if (user == null) {
			System.out.println("�㻹û�е�¼�����¼���ڲ鿴���ﳵ��");
			return;
		}
		System.out.println("��ѡ����ǣ��鿴���ﳵ��");
		DaoService.showAllBuy(user.getId());
	}

	/*
	 * ����Ա��¼ģ��
	 */
	private static void toadmin() {

		if (user == null || !"admin".equals(user.getName())) {
			adminLogin();
			if (user == null || !"admin".equals(user.getName())) {
				System.out.println("�㻹û�е�¼����Ա�ʺţ�������ѡ���¼��");
				return;
			}
			int lx = 13;
			for (int i = 0; i < lx; i++)
				System.out.print("*");
			System.out.print("����Ա��¼�ɹ�");
			for (int i = 0; i < lx; i++)
				System.out.print("*");
			System.out.println();
		}
		if (user == null || !"admin".equals(user.getName())) {
			System.out.println("�㻹û�е�¼����Ա�ʺţ�������ѡ���¼��");
			return;
		}
		System.out.println("�����й���Ȩ�ޣ�����Ҫ�ٴε�¼��");
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
					gname = MainMethods.getScannerToString("��������ƷID�����ƣ�����IDֱ���޸���Ʒ������\n");
					try {
						int id = Integer.parseInt(gname);
						if (DaoService.showGoodsById(id)) {
							System.out.println("������Ҫ�޸���Ʒ����������");
							int count = getSelect2();
							if (DaoService.updateCount(id, count)) {
								System.out.println("�޸���Ʒ���������ɹ���");
								flay = MainMethods.getScannerToString("�Ƿ���������Ʒ��y/n����\n").toLowerCase();
								continue;
							}
						} else {
							System.out.println("�����ڸ�ID����Ʒ����������������Ʒ�����֣�");
						}
					} catch (Exception e) {

					}
					System.out.println("��������Ʒ�۸�");
					int price = getSelect2();
					while (price < 0) {
						System.out.println("��Ʒ�۸���С��0�������������룡");
						price = getSelect2();
					}
					System.out.println("��������Ʒ������");
					int count = getSelect2();
					while (count < 0) {
						System.out.println("��Ʒ��������С��0�������������룡");
						count = getSelect2();
					}
					if (DaoService.addShop(gname, price, count)) {
						System.out.println("�����Ʒ�ɹ���");
					} else {
						System.out.println("����δ֪���󣬺�̨ά����...");
						break;
					}
					flay = MainMethods.getScannerToString("�Ƿ���������Ʒ��y/n����\n").toLowerCase();
				}
				break;
			case 2:
				System.out.println("������Ҫ�޸ĵ�ID��ţ�");
				int pid = getSelect2();
				if (DaoService.showGoodsById(pid)) {
					gname = MainMethods.getScannerToString("��������Ʒ���ƣ�\n");
					System.out.println("��������Ʒ�۸�");
					int price = getSelect2();
					while (price < 0) {
						System.out.println("��Ʒ�۸���С��0�������������룡");
						price = getSelect2();
					}
					System.out.println("��������Ʒ������");
					int count = getSelect2();
					while (count < 0) {
						System.out.println("��Ʒ��������С��0�������������룡");
						count = getSelect2();
					}
					if (DaoService.updeGoods(pid, gname, price, count)) {
						System.out.println("�ɹ��޸���Ʒ����");
						break;
					}
					System.out.println("����δ֪����...");
				}
				break;

			case 3:
				System.out.println("������Ҫɾ����Ʒ��ID:");
				int id = getSelect2();
				if (!DaoService.delGoods(id)) {
					System.out.println("ɾ��ʧ�ܣ������ڸ���Ʒ��");
					break;
				}
				System.out.println("ɾ���ɹ���");
				break;
			case 4:
				DaoService.showGoodList();
				break;
			case 5:
				System.out.println("������Ҫ��ѯ��Ʒ������");
				name = MainMethods.getScannerToString("");
				if (DaoService.showGoodsByName(name))
					;
				break;
			case 6:
				System.out.println("���Ѿ��ɹ��˳�����Ա�������µ�¼��");
				user = null;
				return;

			default:
				System.out.println("�����������������Χ�����������룡����");
				break;
			}
		}
	}

	private static void adminLogin() {
		System.out.println("��ѡ��Ĳ˵��ǣ�����Ա��¼");
		String name = MainMethods.getScannerToString("���������Ա�ʺţ�\n");
		String pwd = MainMethods.getScannerToString("���������Ա���룺\n");
		if (!name.equals("admin")) {
			System.out.println("�����ڸù���Ա��������ѡ��");
			return;
		}
		if (!DaoService.isExitUser(name)) {
			System.out.println("�����ڸù���Ա��������ѡ��");
			return;
		}

		if ((user = DaoService.tologin(name, pwd)) == null) {
			System.out.println("��������ʺŻ��������������ѡ���¼...\n");
			return;
		}
	}

	/**
	 * �û���¼ģ��
	 */
	private static void tologin() {
		if (user != null) {
			System.out.println("���Ѿ���¼��ϵͳ������Ҫ�ٴε�¼��");
			return;
		}
		System.out.println("��ѡ��Ĳ˵��ǣ��û���¼");
		String name = MainMethods.getScannerToString("�������¼�û�����\n");
		String pwd = MainMethods.getScannerToString("�������¼���룺\n");
		if (!DaoService.isExitUser(name)) {
			System.out.println("�û��������ڣ�������ѡ���¼...");
			return;
		}

		if ((user = DaoService.tologin(name, pwd)) == null) {
			System.out.println("��������û������������������ѡ���¼...\n");
			return;
		}
		System.out.println("��¼�ɹ�����ӭʹ��...");
	}

	/**
	 * �û�ע�᷽��
	 */
	private static void toSign() {
		if (user != null) {
			System.out.println("���Ѿ���¼����Ҫ�ظ�ע�ᣡ");
			return;
		}
		System.out.println("��ѡ��Ĳ˵��ǣ�ע��\n��ӭע��...");
		String name = MainMethods.getScannerToString("�������û�����\n");
		while (DaoService.isExitUser(name)) {
			System.out.println("��������û����Ѿ���ʹ�ã����������룺");
			name = MainMethods.getScannerToString("�������û�����\n");
		}
		while (!CheckAll.isUsername(name)) {
			System.out.println("��������û������Ϸ�����������ĸ���֣��»�����ɣ���������3λ,���������룺");
			name = MainMethods.getScannerToString("�������û�����\n");
		}
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,16}$";

		String pwd = MainMethods.getScannerToString("���������룺\n");
		while (!pwd.matches(regex)) {
			System.out.println("����������룬���뺬����ĸ���֣�������3λ,���������룺");
			pwd = MainMethods.getScannerToString("���������룺\n");
		}

		String pwd2 = MainMethods.getScannerToString("��ȷ�����룺\n");
		while (!pwd.equals(pwd2)) {
			System.out.println("��������������벻��ͬ�����ٴ����룡");
			pwd = MainMethods.getScannerToString("���������룺\n");
			while (!pwd.matches(regex)) {
				System.out.println("����������룬���뺬����ĸ���֣�������3λ,���������룺");
				pwd = MainMethods.getScannerToString("���������룺\n");
			}
			pwd2 = MainMethods.getScannerToString("��ȷ�����룺\n");
		}
		if (DaoService.addUser(name, pwd2)) {
			System.out.println("ע��ɹ�,��ӭ����ʹ�ã�");
		} else {
			System.out.println("����δ֪���󣬹���Ա������...");
		}
	}

	/**
	 * �õ��û������ѡ��ţ�����
	 * 
	 * @return
	 */
	private static int getSelect() {
		int select;
		try {
			select = MainMethods.getScannerToInt("");
		} catch (ShopRunTimeException e) {
			System.out.println("��������������Ͳ��ԣ�������ѡ��");
			MainMethods.showMume();
			return getSelect();
		}
		return select;
	}

	/**
	 * ����Ա��������
	 * 
	 * @return
	 */
	private static int getSelect2() {
		int select;
		try {
			select = MainMethods.getScannerToInt("");
		} catch (ShopRunTimeException e) {
			System.out.println("��������������Ͳ��ԣ����������룺");
			return getSelect2();
		}
		return select;
	}
}
