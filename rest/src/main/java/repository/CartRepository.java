package repository;

import model.Cart;

public class CartRepository extends GenericRepository<Cart, CartRepository>  {

	@Override
	protected String getFileName() {
		return "cartData.json";
	}

	@Override
	protected String getKey(Cart e) {
		return e.getCartOwnerId();
	}

}
