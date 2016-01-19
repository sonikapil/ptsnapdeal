--SYNOPSIS
This project is responsible to mock an API responses.  It also includes the mocking of Activemq calls in order to get the responses.
This project also handles the re-direction of API hits on the basis of their availability on Real server or mocking server.

--MOTIVATION
There are some situation when one or more API are not fully developed and we need their response in order to continue our testing. 
This project is capable of providing the required responses.

--INSTALLATION
To develop this project jadler-mocking is being used. In order to use Jalder-mocking methods, we need to add the dependency of jadler-mocking into you pom.

To read more about jadler-mocking :- https://github.com/jadler-mocking/jadler

Also add the dependency of the project, whose API needs to be mocked. After doing so you are ready for mocking.


--SET-UP HAPROXY AS A LOAD BALANCER ON WINDOWS
On windows machines we can setup HAProxy only using Cygwin.
Steps :-

1. Install cygwin.
2. Using the Cygwin setup.exe install GCC and G++
3. Download HaProxy http://haproxy.1wt.eu/
4. Extract the HaProxy archive you downloaded 
5. Go into the extracted folder (cd haproxy.x.y.z) where .x.y.z was the version number.
6. Now compile: make TARGET=linux28
7. If no error occurred  then install: make install
8. Once installed you need a HaProxy Config file.
9. Once you have a HaProxy config file fire up the process using: haproxy.exe -f haproxy.cfg

A sample of haproxy.cfg file is below

global
    daemon
    maxconn 256

defaults
    mode http
    timeout connect 7000ms
    timeout client 7000ms
    timeout client 7000ms
    timeout server 7000ms

frontend http-in
    bind :8086
    default_backend mockServer
	acl api_1 path_end /<api_name>  #this is checking for a sepecific API
	use_backend realServer if api_1 

backend mockServer
    server s1 <mockServer IP/URL>:<Port> maxconn 32
	
backend realServer
    server s2 <realServer IP/URL>:<Port> maxconn 32
	

--CONTRIBUTORS
For any query please feel free to contact me : Faheem Akhtar <mohammad.akhtar@snapdeal.com>
