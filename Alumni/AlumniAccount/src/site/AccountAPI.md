An account may have one of four different states:

- new
- active
- inactive
- retired

Creating an account will initial set the status to `new`.

If the user activates his account within three days from creation, the status will be set to `active`, otherwise the account will be completely removed (deleted from the database).

If, and only if the account is in active state, the user may

- login to his account
- update account information
- delete his account

Delete may remove the account from the database or will set the account's status to the status `retired` and remove essential information which identifies the user (name, email, ...).
This behavior depends on the data which is linked to this account.
If some data has to be kept as information within the system, then the account remains anonymized in the retired state, otherwise it will be completely removed from the database.

If, and only if the account is in active state, the administrator may shift the account into `inactive` state.
This might be used to temporary disable an account, e.g. for further investigation in case of abusing the account.
The administrator might switch back the status to `active`.

Thus, the account service (`AlumniAccount`) offers this API:

`AlumniAccount/api/Accounts`
post: create an account
returns 409 conflict if  the database contains an account with the given email
returns 201 created if the account could be created. If the given loginName existed within the database, then the system will change the loginName to a unique one

`AlumniAccount/api/Accounts/{id}`
get: retriev account

`AlumniAccount/api/Accounts/{id}`
put: update account

`AlumniAccount/api/Accounts/{id}`
delete: delete account (either anonymize and set to retired or totally remove from DB)

`AlumniAccount/api/Accounts/activate/{id}`
put: set account status to `active`

`AlumniAccount/api/Accounts/login/{loginName}/{password}`
get: returns a list of groups, if the user is allowed to login

