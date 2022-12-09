
package Analyse.ObjectModel;

public class Spieler {


	




	
	/**
	 * @clientCardinality 2..6
	 * @clientNavigability NAVIGABLE
	 * @label würfelt mit
	 * @supplierCardinality 2
	 */
	
	private Analyse.ObjectModel.Würfel lnkWürfel;
	/**
	 * @clientCardinality 1
	 * @label führt aus
	 * @link aggregation
	 * @supplierCardinality 0..N
	 * @supplierNavigability NAVIGABLE
	 */
	private Analyse.ObjectModel.Spielzug lnkSpielzug;
/**
 * @clientCardinality 1
 * @clientNavigability NAVIGABLE
 * @label gehört zu
 * @link aggregation
 * @supplierCardinality 4
 * @supplierNavigability NAVIGABLE
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
