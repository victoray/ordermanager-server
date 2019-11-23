## Live Demo
### API endpoints
GET [/api/items](https://ancient-coast-58289.herokuapp.com/api/items) - Returns a list of all available items.  
```
[ 
   { 
      "name":"pen",
      "price":0.45,
      "id":1
   },
   { 
      "name":"book",
      "price":2.45,
      "id":2
   },
   { 
      "name":"phone",
      "price":799.99,
      "id":3
   },
   { 
      "name":"laptop",
      "price":2999.99,
      "id":4
   }
]

```
GET [/api/orders](https://ancient-coast-58289.herokuapp.com/api/orders) - Returns a list of all orders.  
PUT [/api/orders/new](https://ancient-coast-58289.herokuapp.com/api/orders/new) - Add a new order. Accepts an array of order items in this format.  
```
{ 
   "orderItems":[ 
      { 
         "quantity":30,
         "item":{ 
            "id":3,
            "price":1,
            "name":"phone"
         }
      },
      { 
         "quantity":30,
         "item":{ 
            "id":3,
            "price":2,
            "name":"phone"
         }
      }
   ]
}
```
