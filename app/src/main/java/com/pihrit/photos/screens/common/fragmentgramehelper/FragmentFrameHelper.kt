package com.pihrit.photos.screens.common.fragmentgramehelper

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/*
 Original source (Java) by Vasiliy Zukanov
 https://github.com/techyourchance/android-architecture-course
*/
class FragmentFrameHelper(
    private val mActivity: Activity,
    private val mFragmentFrameWrapper: FragmentFrameWrapper,
    private val mFragmentManager: FragmentManager
) {

    private val currentFragment: Fragment?
        get() = mFragmentManager.findFragmentById(fragmentFrameId)

    private val fragmentFrameId: Int
        get() = mFragmentFrameWrapper.getFragmentFrame().id

    fun replaceFragment(newFragment: Fragment) {
        replaceFragment(newFragment, true, false)
    }

    fun replaceFragmentDontAddToBackstack(newFragment: Fragment) {
        replaceFragment(newFragment, false, false)
    }

    fun replaceFragmentAndClearBackstack(newFragment: Fragment) {
        replaceFragment(newFragment, false, true)
    }

    fun navigateUp() {
        // Some navigateUp calls can be "lost" if they happen after the state has been saved
        if (mFragmentManager.isStateSaved) {
            return
        }

        val currentFragment = currentFragment

        if (mFragmentManager.backStackEntryCount > 0) {

            // In a normal world, just popping back stack would be sufficient, but since android
            // is not normal, a call to popBackStack can leave the popped fragment on screen.
            // Therefore, we start with manual removal of the current fragment.
            // Description of the issue can be found here: https://stackoverflow.com/q/45278497/2463035
            removeCurrentFragment()

            if (mFragmentManager.popBackStackImmediate()) {
                // navigated "up" in fragments back-stack
                return
            }
        }

        if (HierarchicalFragment::class.java.isInstance(currentFragment)) {
            val parentFragment = (currentFragment as HierarchicalFragment).getHierarchicalParentFragment()

            if (parentFragment != null) {
                replaceFragment(parentFragment, false, true)
                // navigate "up" to hierarchical parent fragment
                return
            }
        }

        if (mActivity.onNavigateUp()) {
            // navigated "up" to hierarchical parent activity
            return
        }

        // no "up" navigation targets - just treat UP as back press
        mActivity.onBackPressed()
    }

    private fun replaceFragment(newFragment: Fragment, addToBackStack: Boolean, clearBackStack: Boolean) {
        if (clearBackStack) {
            if (mFragmentManager.isStateSaved) {
                // If the state is saved we can't clear the back stack. Simply not doing this, but
                // still replacing fragment is a bad idea. Therefore we abort the entire operation.
                return
            }
            // Remove all entries from back stack
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        val ft = mFragmentManager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(null)
        }

        // Change to a new fragment
        ft.replace(fragmentFrameId, newFragment, null)

        if (mFragmentManager.isStateSaved) {
            // We acknowledge the possibility of losing this transaction if the app undergoes
            // save&restore flow after it is committed.
            ft.commitAllowingStateLoss()
        } else {
            ft.commit()
        }
    }

    private fun removeCurrentFragment() {
        val ft = mFragmentManager.beginTransaction()
        currentFragment?.let { ft.remove(it) }
        ft.commit()

        // not sure it is needed; will keep it as a reminder to myself if there will be problems
        // mFragmentManager.executePendingTransactions();
    }

}