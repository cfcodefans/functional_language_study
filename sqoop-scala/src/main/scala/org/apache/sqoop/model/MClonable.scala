package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

@InterfaceAudience.Private
@InterfaceStability.Unstable trait MClonable {
	/**
	  * Clone object
	  * Invoke with value true for cloning value
	  *
	  * @param cloneWithValue
	  * @return
	  */
	def clone(cloneWithValue: Boolean): Any
}
