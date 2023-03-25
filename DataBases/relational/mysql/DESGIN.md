# Design Databases

## Levels

* Data Modeling
    * Conceptual Model
    * Logical Model
    * Physical Model
* Determine Primary Keys, Foreign Keys, Cascade conditions
* Normalization
    * 1NF -> Replace repeatable rows of column to a new tale.
    * 2NF -> This is the single responsibility of Database tables
    * 3NF -> We should not have a column that it's value is dependant to other columns of the table so if we update one
      we need to update this too.