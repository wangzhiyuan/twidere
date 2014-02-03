/*
 * 				Twidere - Twitter client for Android
 * 
 *  Copyright (C) 2012-2014 Mariotaku Lee <mariotaku.lee@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.content;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

import org.mariotaku.twidere.content.iface.ITwidereContextWrapper;
import org.mariotaku.twidere.content.res.TwidereResources;

public class TwidereContextWrapper extends ContextWrapper implements ITwidereContextWrapper {

	private final Resources mResources;
	private Resources mTwidereResources;
	private final int mThemeResourceId;

	public TwidereContextWrapper(final Context base) {
		this(base, null, getThemeResource(base));
	}

	public TwidereContextWrapper(final Context base, final Resources res) {
		this(base, res, getThemeResource(base));
	}

	public TwidereContextWrapper(final Context base, final Resources res, final int theme) {
		super(base);
		mResources = res;
		mThemeResourceId = theme;
	}

	@Override
	public Resources getResources() {
		if (mTwidereResources != null) return mTwidereResources;
		if (mResources != null) return mTwidereResources = new TwidereResources(this, mResources);
		return mTwidereResources = new TwidereResources(this, super.getResources());
	}

	@Override
	public int getThemeResourceId() {
		return mThemeResourceId;
	}

	private static int getThemeResource(final Context base) {
		if (base instanceof ITwidereContextWrapper)
			return ((ITwidereContextWrapper) base).getThemeResourceId();
		else
			return 0;
	}

}
