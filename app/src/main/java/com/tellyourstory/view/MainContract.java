package com.tellyourstory.view;

interface MainContract {
    interface Controller {
        /**
         * Decides whether the music should stop or start and does it + applies appropriate view changes
         */
        void handleMusicAndView();

        /**
         * Initializes controller with the given view
         *
         * @param view passed to the controller
         */
        void initController(Activity view);

        /**
         * Stops music and changes view appropriately
         */
        void handleStop();

        /**
         * Start changing pictures on button
         */
        void startChangingPictures();

        /**
         * Stop changing pictures on button
         */
        void stopChangingPictures();
    }

    interface Activity {
        /**
         * Updates upper text with the given string
         *
         * @param text string to be set to the upper text view
         */
        void updateTitle(String text);

        /**
         * Updates the bottom text with the given string
         *
         * @param text string to be set to the bottom text view
         */
        void updateBottomText(String text);

        /**
         * Update picture on cry button
         *
         * @param resId the resource id of the picture to set
         */
        void updateCryButton(int resId);
    }
}
