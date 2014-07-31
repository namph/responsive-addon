/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.addons.provider;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.portal.mop.navigation.NavigationService;

import javax.inject.Provider;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class GateInMetaProvider implements juzu.inject.ProviderFactory
{
  public <T> Provider<? extends T> getProvider(final Class<T> implementationType)
  {
    return new Provider<T>() {
      public T get() {
        ExoContainer container = ExoContainerContext.getCurrentContainer();
        T ret = (T)container.getComponentInstance(implementationType);
        if(ret == null)
        {
          PortalContainer portalContainer = PortalContainer.getInstance();
          ret = (T)portalContainer.getComponentInstanceOfType(implementationType);
          if(ret == null)
          {
            RootContainer rootContainer = RootContainer.getInstance();
            ret = (T)rootContainer.getComponentInstanceOfType(implementationType);
          }
        }
        return ret;
      }
    };
  }
}
