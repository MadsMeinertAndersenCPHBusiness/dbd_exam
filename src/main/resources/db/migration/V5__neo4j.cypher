match (a) -[r] -> () delete a, r
match (a) delete a

CREATE (BananaCake:Recipe {name:'BananaCake'})
CREATE (Banana:Ingredient {name:'Banana'})
CREATE (Flour:Ingredient {name:'Flour'})
CREATE (Sugar:Ingredient {name:'Sugar'})

CREATE
  (Banana)-[:USED_IN {amount: 200}]->(BananaCake),
  (Flour)-[:USED_IN {amount: 100}]->(BananaCake),
  (Sugar)-[:USED_IN {amount: 500}]->(BananaCake);