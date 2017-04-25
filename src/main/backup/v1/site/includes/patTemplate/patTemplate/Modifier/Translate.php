<?PHP
/**
 * patTemplate modfifier Translate
 *
 * $Id: Translate.php 1544 2005-12-23 07:01:01Z eddieajau $
 *
 * @package		patTemplate
 * @subpackage	Modifiers
 * @author		Andrew Eddie <eddie.andrew@gmail.com>
 */

/**
 * Implements the Joomla translation function on a var
 *
 * @package		patTemplate
 * @subpackage	Modifiers
 * @author		Andrew Eddie <eddie.andrew@gmail.com>
 */
class patTemplate_Modifier_Translate extends patTemplate_Modifier
{
   /**
	* modify the value
	*
	* @access	public
	* @param	string		value
	* @return	string		modified value
	*/
	function modify( $value, $params = array() )
	{
		global $_LANG;
		if (is_object( $_LANG )) {
			return $_LANG->_( $value );
		} else {
			if (defined( $value )) {
				$value = constant( $value );
			}
			return $value;
		}
	}
}
?>