package com.example.model;

public class FileData {
	private String nick;
	private int fix;
	private int checked;

	public FileData(String nick, int fix, int checked) {
		this.nick = nick;
		this.fix = fix;
		this.checked = checked;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getFix() {
		return fix;
	}

	public void setFix(int fix) {
		this.fix = fix;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "FileData [nick=" + nick + ", fix=" + fix + ", checked=" + checked + "]";
	}

}
