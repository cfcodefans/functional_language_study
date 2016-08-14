package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Marker class that identifies the Configurables in the Sqoop system
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
abstract class Configurable extends MPersistableEntity with MClonable {}
