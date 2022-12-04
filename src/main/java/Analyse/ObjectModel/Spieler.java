
package Analyse.ObjectModel;

public class Spieler {


	/**
	 * @clientCardinality 1
	 * @link aggregation
	 * @supplierCardinality 4 
	 */
	private Analyse.ObjectModel.Wissenstandsanzeiger lnkWissenstandsanzeiger;
	/**
	* @clientCardinality 1
	* @clientNavigability NAVIGABLE
	* @directed true
	* @label spielt mit
	* @link aggregation
	* @supplierCardinality 5
	*/
	private Analyse.ObjectModel.Spielfigur lnkSpielfiguren;

}
