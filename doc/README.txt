
Authors:
- Stefan Feuerhahn
- Manuel Buehrer
- Marco Mittelsdorf

jSML is Java library implementing the Smart Message Language (SML).

SML is a communication protocol for transmitting meter data.  SML
messages are defined in a notation that is similar but not fully
conform to ASN.1. To code messages the SML standard defines encoding
rules that are similar but not equal to the BER encoding.

The jSML library can be used to easily construct SML messages, encode
them, and then send them. Just as easily received messages can be
decoded. jSML implements the SML Transport Layers necessary to
communicate over TCP/IP and serial connections.

-The library and its dependencies are found in the folders
 "build/libs-all/" and "dependencies"
-Javadocs can be found in the build/docs/javadoc folder

For the latest release of this software visit http://www.openmuc.org .

Dependencies:
-------------

jRxTx

This library for serial communication is only needed by jSML if serial
communication is used. jRxTx is a fork of RxTx and is hosted at
https://github.com/openmuc/jrxtx. The library is licensed under the
LGPL(v2.1 or later) + linking exception.

jRxTx consists of a Java part located in the dependencies folder of
the distribution and a native part that is equivalent to that of
RxTx. Note that you have to install the native part of RxTx as
explained in our FAQ.

Using the examples:
-------------------

For examples on how to use jSML see the folder
src/main/java/org/openmuc/jsml/app/ . You can create Eclipse project
files using Gradle as explained in our FAQs and run the samples from
within Eclipse

To execute the server and client in tls/ssl mode you have to set
corresponding properties
-Djavax.net.ssl.keyStore=/path/to/serverKeystore
-Djavax.net.ssl.keyStorePassword="storepass"

For the eHz Reader app run scripts for linux and windows can be found
in the folder "run-scripts"

Develop jSML:
---------------

We use the Gradle build automation tool. The distribution contains a
fully functional gradle build file (“build.gradle”). Thus if you
changed code and want to rebuild a library you can do it easily with
Gradle. Also if you want to import our software into Eclipse you can
easily create Eclipse project files using Gradle. Just follow the
instructions on our FAQ site: https://www.openmuc.org/faq/




