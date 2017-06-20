import subprocess, os

def linevisualize(s):
    r"""
    Determines the form of input, and translates if necessary. Then launches jar implementation of visualization.
    INPUT:
    - ``s`` -- may be passed either as a string of space delimited neural codes, or a list of neural codes
    OUTPUT:
        None
    EXAMPLES:
        sage: visualize('110 010 011')
        sage: visualize(['110', '010', '011'])
    """
    if type(s) is list:
        args = translate(s)
    else:
        args = s
    subprocess.call(['java', '-jar', os.getcwd() + '/Neural_Line.jar', args])