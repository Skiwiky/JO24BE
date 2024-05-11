package com.studi.sapioce.JO24BE.pojo.dto;

import com.studi.sapioce.JO24BE.pojo.DataBank;
import com.studi.sapioce.JO24BE.pojo.User;

public class UserPaiementDTO {
	 
		private User user;
	    private DataBank dataBanks;
	    
	    public UserPaiementDTO() {
	    }

	    public UserPaiementDTO(User user, DataBank dataBanks) {
	        this.user = user;
	        this.dataBanks = dataBanks;
	    }
	    
	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public DataBank getDataBanks() {
	        return dataBanks;
	    }

	    public void setDataBanks(DataBank dataBanks) {
	        this.dataBanks = dataBanks;
	    }
}
