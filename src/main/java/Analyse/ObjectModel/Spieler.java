
package Analyse.ObjectModel;

public class Spieler {


	



	/**
	 * @clientCardinality 1 
	 * @label führt aus
	 * @link aggregation
	 * @supplierCardinality 0..N
	 * @supplierNavigability NAVIGABLE_EXPLICITLY
	 */
	private Analyse.ObjectModel.Spielzug lnkSpielzug;
/**
	 * @clientCardinality 1
	 * @label besitzt
	 * @link aggregation
	 * @supplierCardinality 4
	 * @supplierNavigability NAVIGABLE_EXPLICITLY
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
