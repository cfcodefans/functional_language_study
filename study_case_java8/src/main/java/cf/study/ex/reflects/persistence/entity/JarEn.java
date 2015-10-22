package cf.study.ex.reflects.persistence.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import misc.Jsons;

@Entity
@Table(name = "jar_en")
// @IdClass(JarEnIdClz.class)
public class JarEn {

	public JarEn() {

	}

	@Id
	public long id;

	@Basic
	public String filename;

	@Basic
	public String specTitle;
	@Basic
	public String specVersion;
	@Basic
	public String specVendor;
	@Basic
	public String implTitle;
	@Basic
	public String implVersion;
	@Basic
	public String implVendor;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((implTitle == null) ? 0 : implTitle.hashCode());
		result = prime * result + ((implVersion == null) ? 0 : implVersion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JarEn other = (JarEn) obj;
		if (id != other.id)
			return false;
		if (implTitle == null) {
			if (other.implTitle != null)
				return false;
		} else if (!implTitle.equals(other.implTitle))
			return false;
		if (implVersion == null) {
			if (other.implVersion != null)
				return false;
		} else if (!implVersion.equals(other.implVersion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Jsons.toString(this);
	}
}
