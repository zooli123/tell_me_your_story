package com.tellyourstory;

interface MainContract {
    interface Controller {
        /**
         * Decides whether the music should stop or start and does it
         */
        void handleMusic();

        /**
         * Initializes controller with the given view
         *
         * @param view passed to the controller
         */
        void initController(Activity view);

        /**
         * Stops music
         */
        void stopMusic();

        void startChangingPictures();

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

        void updateCryButton(int resId);
    }
}
