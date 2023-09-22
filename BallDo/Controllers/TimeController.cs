using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;
using System.ComponentModel.DataAnnotations;

namespace BallDo.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TeamController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public TeamController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet("{id}")]
        public IActionResult GetTeam(int id)
        {
            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }

            var teamDTO = new TeamDTO
            {
                Id = team.Id,
                Name = team.Name
            };

            return Ok(teamDTO);
        }

        [HttpPost]
        public IActionResult CreateTeam(TeamDTO teamDTO)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var team = new Team
            {
                Name = teamDTO.Name
            };

            _context.Teams.Add(team);
            _context.SaveChanges();

            return CreatedAtAction(nameof(GetTeam), new { id = team.Id }, team);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateTeam(int id, TeamDTO teamDTO)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }

            team.Name = teamDTO.Name;
            _context.SaveChanges();

            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteTeam(int id)
        {
            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }

            _context.Teams.Remove(team);
            _context.SaveChanges();

            return NoContent();
        }
    }
}
