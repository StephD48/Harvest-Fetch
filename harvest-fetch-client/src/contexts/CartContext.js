import { createContext, useState } from "react";

export const CartContext = createContext({
    orderItems: [],
    addItemToOrder: () => {},
    removeItemFromOrder: () => {},
    cartItemCounter: 0,
});

const CartProvider = ({ children }) => {
    const [orderItems, setOrderItems] = useState([]);
    

    const addItemToOrder = (item) => {    // if item is in the cart it will add another to the cart and update the quantity/ if Item is not in the cart it will add 1 item to the card
        const existingItem = orderItems.find((i) => i.productId === item.productId);

        if(existingItem) {
            setOrderItems(
                orderItems.map((i) =>
                i.productId === existingItem.productId ? { ...i, quantity: i.quantity + 1 } : i 
                )
            );
        }else {
            setOrderItems([...orderItems, { ...item, quantity : 1 }]);
        }
        
    };

    const removeItemFromOrder = (item) => {    // if item is in the cart it will remove it from the cart and update the quantity in the cart
        const existingItem = orderItems.find((i) => i.productId === item.productId);

        if (existingItem.quantity === 1) {
            setOrderItems(orderItems.filter((i) => i.productId !== item.productId));
        }else {
            setOrderItems (
                orderItems.map((i) =>
                i.productId === existingItem.productId ? { ...i, quantity: i.quantity - 1 } : 1)
            );
        } 
       
        
    };

    const cartItemCounter = orderItems.reduce((acc, item) => acc + item.quantity, 0); // calculate cart items count
    const getItemCount = () => {
        return orderItems.reduce((acc, item) => acc + item.quantity, 0);
    }

    return(
        <CartContext.Provider value={{ orderItems, addItemToOrder, removeItemFromOrder, cartItemCounter }}>{ children }</CartContext.Provider>
       
    );

};

export default CartProvider;