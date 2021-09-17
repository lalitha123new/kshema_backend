package com.screener.app.entity;

public class Logintemp {
		
		private String mail;
		private String hospitalname;
		private long phone;
		private String hospital_address;
		private String hospital_type;
		
		public String getHospital_type() {
			return hospital_type;
		}
		public void setHospital_type(String hospital_type) {
			this.hospital_type = hospital_type;
		}
		public String getHospital_address() {
			return hospital_address;
		}
		public void setHospital_address(String hospital_address) {
			this.hospital_address = hospital_address;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getHospitalname() {
			return hospitalname;
		}
		public void setHospitalname(String hospitalname) {
			this.hospitalname = hospitalname;
		}
		public long getPhone() {
			return phone;
		}
		public void setPhone(long phone) {
			this.phone = phone;
		}
		
}