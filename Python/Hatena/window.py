#!/usr/bin/env python
# coding: utf-8
import pygame
from pygame.locals import *
import sys
 
SCREEN_SIZE = (800, 600)  # 画面サイズ(640,480)
 
# Pygameを初期化
pygame.init()
# SCREEN_SIZEの画面を作成
screen = pygame.display.set_mode(SCREEN_SIZE)
# タイトルバーの文字列をセット
pygame.display.set_caption(u'make window')
 
# ゲームループ
while True:
    screen.fill((0,0,255))   # 画面を青色で塗りつぶす
    pygame.display.update()  # 画面を更新
    # イベント処理
    for event in pygame.event.get():
        if event.type == QUIT:  # 終了イベント
            sys.exit()
