# Reverse_Polish_Notation
We would like you to create a web application with two routes /rpn and reverse_polish_notation. Both routes should perform the same action, given Reverse Polish Notation argument(s) op the route should respond with the correct value.

-If the combination of operands or operators are invalid the calculator respond with a 400
-the previous calculation in follow on calculations :reserve _ as a placeholder for the previous HTTP result.

Test Cases:
  /rpn?op=14&op=3&op=x&op=8&op=+  200 OK 50 
  
  /rpn?op=7&op=3&op=x&op=8&op=+  200 OK 29 
  
  /rpn?op=$$$  400 Bad Request 
  
  /rpn?op=7&op=3&op=x  200 OK 21
  
  /rpn?op=_&op=8&op=+  200 OK 29
