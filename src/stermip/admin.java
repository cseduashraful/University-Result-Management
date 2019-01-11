package stermip;

import Functions.dbManager;

public class admin extends users {

	public void defaultPhoto(int sr) {
		// TODO Auto-generated method stub
		dbManager.defaultPhoto(this.getReg_id(), sr);
	}

}
