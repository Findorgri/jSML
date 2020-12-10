/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.app;

import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509TrustManager;

public final class XTrustProvider extends Provider {

    private static final long serialVersionUID = -2995992632572849710L;

    private static final String NAME = "XTrustJSSE";
    private static final String INFO = "XTrust JSSE Provider (implements trust factory with truststore validation disabled)";
    private static final double VERSION = 1.0D;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public XTrustProvider() {
        super(NAME, VERSION, INFO);

        AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                put("TrustManagerFactory." + TrustManagerFactoryImpl.getAlgorithm(),
                        TrustManagerFactoryImpl.class.getName());
                return null;
            }
        });
    }

    public static void install() {
        if (Security.getProvider(NAME) == null) {
            Security.insertProviderAt(new XTrustProvider(), 2);
            Security.setProperty("ssl.TrustManagerFactory.algorithm", TrustManagerFactoryImpl.getAlgorithm());
        }
    }

    public static final class TrustManagerFactoryImpl extends TrustManagerFactorySpi {
        public TrustManagerFactoryImpl() {
        }

        public static String getAlgorithm() {
            return "XTrust509";
        }

        @Override
        protected void engineInit(KeyStore keystore) throws KeyStoreException {
        }

        @Override
        protected void engineInit(ManagerFactoryParameters mgrparams) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException(
                    XTrustProvider.NAME + " does not use ManagerFactoryParameters");
        }

        @Override
        protected TrustManager[] engineGetTrustManagers() {
            return new TrustManager[] { new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            } };
        }
    }
}
