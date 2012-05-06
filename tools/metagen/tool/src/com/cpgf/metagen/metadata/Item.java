package com.cpgf.metagen.metadata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Item {
	private String literalName;
	private String primaryName;
	private String qualifiedName;
	private EnumCategory category;
	private EnumVisibility visibility;
	private String location;
	private String namespace;
	private boolean isStatic;
	private boolean isConst;
	private CppClass owner;

	protected Item(EnumCategory category, String name) {
		if(name == null) {
			name = "";
		}

		this.category = category;
		this.literalName = name;
		this.primaryName = getItemBaseName(name);
	}

	private static String getItemBaseName(String name) {
		if(name == null) {
			return "";
		}
		if(name.indexOf('@') >= 0) {
			return "";
		}
		
		Pattern pattern = Pattern.compile("^.*\\b(\\w+)$");
		Matcher matcher = pattern.matcher(name);
		if(matcher.matches()) {
			return matcher.group(1);
		}
		else {
			return name;
		}
	}
	
	public String getPrimaryName() {
		return primaryName;
	}

	public String getLiteralName() {
		return literalName;
	}

	public String getQualifiedName() {
		if(this.qualifiedName == null) {
			this.qualifiedName = this.getPrimaryName();
			if(this.qualifiedName.equals("")) {
				return this.qualifiedName;
			}

			CppClass parent = this.getOwner();
			if(parent == null) {
				if(! this.getNamespace().equals("")) {
					this.qualifiedName = this.getNamespace() + "::" + this.qualifiedName;
				}
			}
			else {
				while(parent != null) {
					String parentQualifiedname = parent.getQualifiedName();
					if(parentQualifiedname == null || parentQualifiedname.equals("")) {
						break;
					}
					this.qualifiedName = parentQualifiedname + "::" + this.qualifiedName;
					parent = parent.getOwner();
				}
			}
		}

		return this.qualifiedName;
	}

	public EnumCategory getCategory() {
		return category;
	}

	public EnumVisibility getVisibility() {
		return visibility;
	}
	
	public void setVisibility(EnumVisibility visibility) {
		this.visibility = visibility;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNamespace() {
		if(this.namespace == null) {
			this.namespace = "";
		}

		return this.namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isConst() {
		return isConst;
	}

	public void setConst(boolean isConst) {
		this.isConst = isConst;
	}

	public CppClass getOwner() {
		return owner;
	}

	public void setOwner(CppClass owner) {
		this.owner = owner;
	}

	public boolean isConstructor() {
		return this.getCategory() == EnumCategory.Constructor;
	}
	
	public boolean isDestructor() {
		return this.getCategory() == EnumCategory.Destructor;
	}
	
	public boolean isClass() {
		return this.getCategory() == EnumCategory.Class;
	}
	
	public boolean isConstant() {
		return this.getCategory() == EnumCategory.Constant;
	}
	
	public boolean isEnum() {
		return this.getCategory() == EnumCategory.Enum;
	}
	
	public boolean isField() {
		return this.getCategory() == EnumCategory.Field;
	}
	
	public boolean isMethod() {
		return this.getCategory() == EnumCategory.Method;
	}
	
	public boolean isOperator() {
		return this.getCategory() == EnumCategory.Operator;
	}

}
