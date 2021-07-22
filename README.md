<p align="center">Management application</p>

### Setup

<p>
In order to start the application run this command in the root folder:

```Shell
docker-compose up
```

</p>

### Login

Basic authentication is in place:
<p>Username = user</p>
<p>Password = password</p>

Really secure, I know.

### API

Once that is done, the available API is as follows:

<p>
To add consent preference for a customer using POST request:

```Shell
http://localhost:8080/management/customer/{customer_id}/add/consent
```

</p>

<p>
Fetch customer consents for a certain customerId using GET request:

```Shell
http://localhost:8080/management/customer/{customer_id}/get/consents
```

</p>

<p>
Fetch all customers consent values for a certain ConsentType using GET request:

```Shell
http://localhost:8080/management/consent/get/{consent_type}?page={page_number}
```

By default, the page size is 10 but if needed, can be changed adding the following parameters:

```Shell
&size={page_size}
```

</p>

<p>
Reaching Swagger UI can be done by accessing:

```Shell
http://localhost:8080/management/swagger-ui.html
```

</p>
