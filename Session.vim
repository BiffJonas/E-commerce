let SessionLoad = 1
let s:so_save = &g:so | let s:siso_save = &g:siso | setg so=0 siso=0 | setl so=-1 siso=-1
let v:this_session=expand("<sfile>:p")
silent only
silent tabonly
cd ~/repos/Java/rest
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
let s:shortmess_save = &shortmess
if &shortmess =~ 'A'
  set shortmess=aoOA
else
  set shortmess=aoO
endif
badd +1 ~/repos/Java/rest
badd +85 src/main/java/com/ecommerce/rest/controller/CommerceController.java
badd +1 src/main/java/com/ecommerce/rest/repository/WaresRepository.java
badd +26 src/main/java/com/ecommerce/rest/model/Ware.java
badd +9 src/main/java/com/ecommerce/rest/repository/UserRepository.java
badd +1 src/main/java/com/ecommerce/rest/exception/WareNotFoundException.java
badd +3 src/main/java/com/ecommerce/rest/exception/ResourceNotFoundException.java
badd +44 src/main/java/com/ecommerce/rest/model/User.java
badd +43 src/main/java/com/ecommerce/rest/controller/Usercontroller.java
badd +2 src/main/java/com/ecommerce/rest/exception/UserNotFoundException.java
argglobal
%argdel
$argadd ~/repos/Java/rest
edit src/main/java/com/ecommerce/rest/controller/Usercontroller.java
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd w
let &splitbelow = s:save_splitbelow
let &splitright = s:save_splitright
wincmd t
let s:save_winminheight = &winminheight
let s:save_winminwidth = &winminwidth
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe 'vert 1resize ' . ((&columns * 105 + 106) / 212)
exe 'vert 2resize ' . ((&columns * 106 + 106) / 212)
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 43 - ((24 * winheight(0) + 25) / 50)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 43
normal! 0
lcd ~/repos/Java/rest
wincmd w
argglobal
if bufexists(fnamemodify("~/repos/Java/rest/src/main/java/com/ecommerce/rest/exception/UserNotFoundException.java", ":p")) | buffer ~/repos/Java/rest/src/main/java/com/ecommerce/rest/exception/UserNotFoundException.java | else | edit ~/repos/Java/rest/src/main/java/com/ecommerce/rest/exception/UserNotFoundException.java | endif
if &buftype ==# 'terminal'
  silent file ~/repos/Java/rest/src/main/java/com/ecommerce/rest/exception/UserNotFoundException.java
endif
balt ~/repos/Java/rest/src/main/java/com/ecommerce/rest/exception/WareNotFoundException.java
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 7 - ((6 * winheight(0) + 25) / 50)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 7
normal! 015|
lcd ~/repos/Java/rest
wincmd w
exe 'vert 1resize ' . ((&columns * 105 + 106) / 212)
exe 'vert 2resize ' . ((&columns * 106 + 106) / 212)
tabnext 1
if exists('s:wipebuf') && len(win_findbuf(s:wipebuf)) == 0 && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20
let &shortmess = s:shortmess_save
let &winminheight = s:save_winminheight
let &winminwidth = s:save_winminwidth
let s:sx = expand("<sfile>:p:r")."x.vim"
if filereadable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &g:so = s:so_save | let &g:siso = s:siso_save
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
